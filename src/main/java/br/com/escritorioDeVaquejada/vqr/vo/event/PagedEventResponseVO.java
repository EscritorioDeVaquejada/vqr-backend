package br.com.escritorioDeVaquejada.vqr.vo.event;

import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PagedEventResponseVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private List<EventResponseVO> content;
    private Integer number;
    private Sort sort;
    private Boolean first;
    private Boolean last;
    private Integer numberOfElements;
    private Boolean empty;

    public PagedEventResponseVO() {
    }

    public PagedEventResponseVO(
            Long totalElements,
            Integer totalPages,
            Integer size,
            List<EventResponseVO> content,
            Integer number,
            Sort sort,
            Boolean first,
            Boolean last,
            Integer numberOfElements,
            Boolean empty) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
        this.content = content;
        this.number = number;
        this.sort = sort;
        this.first = first;
        this.last = last;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
    }

    public List<EventResponseVO> getContent() {
        return content;
    }

    public void setContent(List<EventResponseVO> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PagedEventResponseVO that = (PagedEventResponseVO) object;
        return Objects.equals(totalElements, that.totalElements) && Objects.equals(totalPages, that.totalPages) && Objects.equals(size, that.size) && Objects.equals(content, that.content) && Objects.equals(number, that.number) && Objects.equals(sort, that.sort) && Objects.equals(first, that.first) && Objects.equals(last, that.last) && Objects.equals(numberOfElements, that.numberOfElements) && Objects.equals(empty, that.empty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalElements, totalPages, size, content, number, sort, first, last, numberOfElements, empty);
    }
}
