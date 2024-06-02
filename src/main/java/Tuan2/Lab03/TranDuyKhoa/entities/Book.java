package Tuan2.Lab03.TranDuyKhoa.entities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @NotNull(message = "id là bắt buộc")
    private Long id;
    @NotBlank(message = "title là bắt buộc")
    private String title;
    @NotBlank(message = "author là bắt buộc")
    private String author;
    @NotNull(message = "price là bắt buộc")
    private Double price;
    @NotBlank(message = "category là bắt buộc")
    private String category;
}
