package br.com.escritorioDeVaquejada.vqr.representation;

import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

/**
 * Class representing the structure of a paginated response containing event data,
 * utilized in Swagger OpenAPI Specification annotations.
 * <p>
 * This class describes the properties returned by Controller methods when sending
 * a paginated list of events. It does not represent the actual data sent in the
 * response but serves as an informative structure for API documentation.
 * <p>
 * This implementation represents the {@code Page<EventResponseVO>} class,
 * encapsulating the 'content' and 'page' attributes, as configured by
 * {@code @EnableSpringDataWebSupport(pageSerializationMode =
 * EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)} in WebConfig.java.
 */
@Schema(description = "Represents a paginated response containing event data. " +
        "This object returns a list of events in HTTP responses, along with pagination information.")
public class PagedEventResponse {
    private Page page;
    @Schema(description = "A list of event data objects returned in the current page.")
    private List<EventResponseVO> content;

    public PagedEventResponse() {
    }

    public PagedEventResponse(Page page, List<EventResponseVO> content) {
        this.page = page;
        this.content = content;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<EventResponseVO> getContent() {
        return content;
    }

    public void setContent(List<EventResponseVO> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PagedEventResponse that = (PagedEventResponse) object;
        return Objects.equals(page, that.page) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, content);
    }
}
