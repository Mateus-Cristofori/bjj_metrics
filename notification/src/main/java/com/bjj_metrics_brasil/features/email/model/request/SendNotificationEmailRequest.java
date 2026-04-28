package com.bjj_metrics_brasil.features.email.model.request;

import com.bjj_metrics_brasil.features.email.model.Enum.EmailActionEnum;
import com.bjj_metrics_brasil.templates.model.Enum.TemplatesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationEmailRequest {

    private String to;
    private String subject;
    private TemplatesEnum template;
    private EmailActionEnum action;
}
