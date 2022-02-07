package uk.co.mycompany.healthservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Location {

	@JsonProperty("destination_addresses")
	private List<String> destinationAddresses;

	@JsonProperty("origin_addresses")
	private List<String> originAddresses;

	private List<Row> rows;

	private String status;

	@Data
	public static class Row {
		List<Element> elements;
	}

	@Data
	public static class Element {

		private Distance distance;

		private Duration duration;

		private String status;

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

	@Data
	public static class TextValue {

		private String text;

		private long value;

		public TextValue() {
			text = "";
			value = 0;
		}

	}
}