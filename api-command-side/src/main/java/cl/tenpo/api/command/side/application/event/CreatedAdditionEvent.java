package cl.tenpo.api.command.side.application.event;

import lombok.Value;

import java.util.Date;

@Value
public class CreatedAdditionEvent implements Event {
	private long operationId;

	private int[] values;

	private int result;

	private String createdBy;

	private Date createdDate;

}
