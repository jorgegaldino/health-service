package uk.co.mycompany.health.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	@JsonProperty("destination_addresses")
	private List<String> destinationAddresses;

	@JsonProperty("origin_addresses")
	private List<String> originAddresses;

	private List<Row> rows;

	private String status;

	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}

	public void setDestinationAddresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}

	public List<String> getOriginAddresses() {
		return originAddresses;
	}

	public void setOriginAddresses(List<String> originAddresses) {
		this.originAddresses = originAddresses;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static class Row {
		List<Element> elements;

		public List<Element> getElements() {
			return elements;
		}

		public void setElements(List<Element> elements) {
			this.elements = elements;
		}
	}

	public static class Element {

		private Distance distance;

		private Duration duration;

		private String status;

		public Distance getDistance() {
			return distance;
		}

		public void setDistance(Distance distance) {
			this.distance = distance;
		}

		public Duration getDuration() {
			return duration;
		}

		public void setDuration(Duration duration) {
			this.duration = duration;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}

	public static class Distance extends TextValue {

		public Distance() {
			super();
		}
	}

	public static class Duration extends TextValue {

		public Duration() {
			super();
		}
	}

	public static class TextValue {

		private String text;

		private long value;

		public TextValue() {
			text = "";
			value = 0;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public long getValue() {
			return value;
		}

		public void setValue(long value) {
			this.value = value;
		}
	}
}