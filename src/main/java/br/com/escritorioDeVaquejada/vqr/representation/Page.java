package br.com.escritorioDeVaquejada.vqr.representation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Class representing the structure of a Page object for use in
 * Swagger OpenAPI Specification annotations.
 * <p>
 * This class describes the properties returned by Controller methods
 * when sending a paginated list. It does not represent the actual
 * data sent in the response, but serves as an informative structure
 * for API documentation.
 * <p>
 * This implementation is utilized to represent the 'page' property,
 * as configured by {@code @EnableSpringDataWebSupport(pageSerializationMode =
 * EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)} in WebConfig.java.
 */
@Schema(description = "Represents a paginated response structure containing metadata about the " +
        "pagination state.")
public class Page {
    @Schema(
            description = "The maximum number of items that can be delivered on a single page.",
            defaultValue = "20"
    )
    private Integer size;

    @Schema(description = "The current page number (zero-based index).", defaultValue = "0")
    private Integer number;

    @Schema(description = "The total number of entities in the database that match the query, " +
            "regardless of pagination.")
    private Integer totalElements;

    @Schema(description = "The total number of pages available based on the current page size.")
    private Integer totalPages;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Page page = (Page) object;
        return Objects.equals(size, page.size) && Objects.equals(number, page.number) && Objects.equals(totalElements, page.totalElements) && Objects.equals(totalPages, page.totalPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number, totalElements, totalPages);
    }

    public Page(Integer size, Integer number, Integer totalElements, Integer totalPages) {
        this.size = size;
        this.number = number;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public Page() {
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
