package org.example.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.example.Evaluator;
import org.example.ShuntingYard;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        // Заголовок
        H1 title = new H1("Калькулятор выражений");
        title.getStyle().set("color", "#2c3e50");

        // Поле ввода
        TextField inputField = new TextField();
        inputField.setLabel("Введите математическое выражение");
        inputField.setPlaceholder("например: (11 + 18) * 20 - 2 ");
        inputField.setWidth("300px");

        // Сообщение об ошибке
        Div errorMessage = new Div();
        errorMessage.getStyle()
                .set("color", "red")
                .set("margin-top", "5px");

        // Кнопка вычисления
        Button calculateButton = new Button("Вычислить");
        calculateButton.getStyle()
                .set("background-color", "#3498db")
                .set("color", "white")
                .set("border-radius", "5px");

        calculateButton.addClickListener(e -> {
            String expression = inputField.getValue();
            try {
                List<String> postfix = ShuntingYard.infixToPostfix(expression);
                double result = Evaluator.evaluate(postfix);
                errorMessage.setText("Результат: " + result); // показываем результат вместо уведомления
            } catch (IllegalArgumentException ex) {
                errorMessage.setText("Ошибка ввода: " + ex.getMessage());
            } catch (ArithmeticException ex) {
                errorMessage.setText("Ошибка вычисления: " + ex.getMessage());
            } catch (Exception ex) {
                errorMessage.setText("Неизвестная ошибка: " + ex.getMessage());
            }
        });

        // Контейнер для центрирования
        Div container = new Div(title, inputField, errorMessage, calculateButton);
        container.getStyle()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("gap", "10px")
                .set("width", "100%")
                .set("height", "100%");

        add(container);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background-color", "#ecf0f1");
    }
}

