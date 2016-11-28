package ru.javawebinar.topjava.to;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javawebinar.topjava.model.Meal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

public class MealTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotNull(message = "{error.mealTo.dateTime.notnull}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @Length(min = 3, max = 50, message = "{error.mealTo.description.length}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я][a-zа-я0-9-_ +]{2,}$", message = "{error.mealTo.description}")
    @SafeHtml
    private String description;

    @NotNull(message = "{error.mealTo.calories.notnull}")
    @Range(min = 0, max = 3000, message = "{error.mealTo.calories.range}")
    private Integer calories;

    public MealTo() {
    }

    public MealTo(Meal meal) {
        this.id = meal.getId();
        this.dateTime = meal.getDateTime();
        this.description = meal.getDescription();
        this.calories = meal.getCalories();
    }

    public MealTo(Integer id, LocalDateTime ldt, String description, Integer calories ) {
        this.id = id;
        this.dateTime = ldt;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
