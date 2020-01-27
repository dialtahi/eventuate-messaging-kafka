package io.eventuate.messaging.kafka.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EventuateKafkaMultiMessages {
  private List<EventuateKafkaMultiMessagesHeader> headers;
  private List<EventuateKafkaMultiMessage> messages;

  public EventuateKafkaMultiMessages(List<EventuateKafkaMultiMessage> messages) {
    this(Collections.emptyList(), messages);
  }

  public EventuateKafkaMultiMessages(List<EventuateKafkaMultiMessagesHeader> headers, List<EventuateKafkaMultiMessage> messages) {
    this.headers = headers;
    this.messages = messages;
  }

  public List<EventuateKafkaMultiMessagesHeader> getHeaders() {
    return headers;
  }

  public List<EventuateKafkaMultiMessage> getMessages() {
    return messages;
  }

  public int estimateSize() {
    return
            headers.stream().map(KeyValue::estimateSize).reduce(0, (a, b) -> a + b) +
            messages.stream().map(KeyValue::estimateSize).reduce(0, (a, b) -> a + b);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(headers, messages);
  }
}