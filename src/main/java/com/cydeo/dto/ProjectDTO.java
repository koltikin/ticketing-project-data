package com.cydeo.dto;
import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotBlank
    private String projectName;
    @NotBlank
    private String projectCode;
    @NotNull
    private UserDTO projectManager;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate, projectEndDate;
    @NotBlank(message = "enter project detail")
    private String projectDetail;
    private Status projectStatus;

    private int unfinishedCount;
    private int completedCount;


}
