package com.bjj_metrics_brasil.features.account.model.request;

import com.bjj_metrics_brasil.features.account.model.Enum.EmailActionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationEmailRequest {

    private EmailActionEnum action;
}
