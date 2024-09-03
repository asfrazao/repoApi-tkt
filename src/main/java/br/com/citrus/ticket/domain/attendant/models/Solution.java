package br.com.citrus.ticket.domain.attendant.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution {
    private String ocurrencyName;
    private String id;
    private String name;
    private String description;
    private String note;
    private int estimatedTime;
    private String timeUnit;
    private boolean alternativeFlow;
    private boolean active;
    private String ocurrencyId;
    private String type;
    private Date createdAt;
    private String creatorId;
    private boolean mandatoryClient;
    private String extraFieldGroupId;
    private String formId;
    private boolean desableFlowObjectActions;
    private String sectorId;
    private boolean updateProcessObject;
    private String endedProcessInOfferFaseId;
    private String canceledProcessInOfferFaseId;
}
