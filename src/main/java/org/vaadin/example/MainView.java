package org.vaadin.example;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends Div {

    private final TextField textField = new TextField();
    private final Binder<TestData> binder = new Binder<>(TestData.class);
    private final TextField gridEditTextField1 = new TextField();
    private final TextField gridEditTextField2 = new TextField();
    private final List<TestData> gridData = List.of(new TestData());
    private final Grid<TestData> grid = new Grid<>();

    public MainView() {
        setSizeFull();
        textField.setWidthFull();
        final Editor<TestData> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(false);
        grid.setItems(gridData);
        grid.addColumn(TestData::getField1).setHeader("Field 1").setEditorComponent(gridEditTextField1);
        grid.addColumn(TestData::getField2).setHeader("Field 2").setEditorComponent(gridEditTextField2);
        add(textField, grid);
        gridData.stream().findFirst().ifPresent(editor::editItem);
        textField.focus();
    }

    public static class TestData {
        private String field1;
        private String field2;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }
    }
}
