package com.tvmsoftware.eventslibrary;

import com.tvmsoftware.eventslibrary.model.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplicationCreatedEvent extends Event {
    protected ApplicationType applicationType;
    protected Integer numberOfApplicants;
}


