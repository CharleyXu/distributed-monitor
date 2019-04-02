package com.xu.admin.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.domain.values.StatusInfo;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author CharleyXu Created on 2019/3/8.
 *
 * 自定义通知
 */
@Component
@Slf4j
public class CustomNotifier extends AbstractStatusChangeNotifier {

  public CustomNotifier(InstanceRepository repository) {
    super(repository);
  }

  @Override
  protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {
    return Mono.fromRunnable(() -> {
      if (instanceEvent instanceof InstanceStatusChangedEvent) {
        log.info("Instance {} ({}) is {}", instance.getRegistration().getName(),
            instanceEvent.getInstance(),
            ((InstanceStatusChangedEvent) instanceEvent).getStatusInfo().getStatus());

        String status = ((InstanceStatusChangedEvent) instanceEvent).getStatusInfo().getStatus();

        switch (status) {
          // 健康检查没通过
          case StatusInfo
              .STATUS_DOWN:
            log.warn("发送 健康检查没通过 的通知！");
            break;
          // 服务离线
          case StatusInfo.STATUS_OFFLINE:
            log.warn("发送 服务离线 的通知！");
            break;
          //服务上线
          case StatusInfo.STATUS_UP:
            log.warn("发送 服务上线 的通知！");
            break;
          // 服务未知异常
          case StatusInfo.STATUS_UNKNOWN:
            log.warn("发送 服务未知异常 的通知！");
            break;
          default:
            break;
        }

      } else {
        log
            .info("Instance00 {} ({}) {}", instance.getRegistration().getName(),
                instanceEvent.getInstance(),
                instanceEvent.getType());
      }
    });
  }
}
