package org.example.jms.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatus implements Serializable {

    private String taskId;
    private String taskName;
    private float percentageComplete;
    private Status status;

    public enum Status {
        SUBMITTED, STARTED, RUNNING, FINISHED, TERMINATED
    }
}
