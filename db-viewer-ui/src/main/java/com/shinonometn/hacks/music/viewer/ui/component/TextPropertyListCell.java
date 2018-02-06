package com.shinonometn.hacks.music.viewer.ui.component;

import javafx.scene.control.ListCell;

import java.util.function.Function;

public class TextPropertyListCell<T> extends ListCell<T> {

    private TextPropertyListCell() {

    }

    @Override
    protected final void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        String result = compute(item);

        if (empty || item == null || result == null) {
            setText(null);
        } else {
            setText(result);
        }
    }

    protected String compute(T item){
        return item.toString();
    }

    public static <T> TextPropertyListCell<T> of(Function<T, String> function) {
        return new TextPropertyListCell<T>() {
            @Override
            protected String compute(T item) {
                return item == null ? null : function.apply(item);
            }
        };
    }
}
