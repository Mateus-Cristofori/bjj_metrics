package com.bjj_metrics_brasil.client.model.request;

import com.bjj_metrics_brasil.client.model.Enum.EmailActionEnum;
import com.bjj_metrics_brasil.templates.model.Enum.TemplatesEnum;
import java.util.HashMap;
import java.util.Map;
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

    @Builder.Default
    private Map<String, Object> templateData = new HashMap<>();
}
