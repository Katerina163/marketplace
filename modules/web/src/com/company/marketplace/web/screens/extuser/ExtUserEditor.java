package com.company.marketplace.web.screens.extuser;

import com.company.marketplace.entity.Buyer;
import com.company.marketplace.entity.ExtUser;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.app.security.user.edit.UserEditor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Subscribe;

import javax.inject.Inject;
import java.util.Objects;

public class ExtUserEditor extends UserEditor {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataManager dataManager;

    @Subscribe("buyerButton")
    public void onBuyerButtonClick(Button.ClickEvent event) {
        ExtUser user = (ExtUser) getItem();
        if (Objects.nonNull(user.getBuyer())) {
            Buyer buyer = dataManager.reload(user.getBuyer(), "_local");
            screenBuilders.editor(Buyer.class, this)
                    .editEntity(buyer)
                    .withOpenMode(OpenMode.DIALOG)
                    .build()
                    .show();
        } else {
            screenBuilders.editor(Buyer.class, this)
                    .newEntity()
                    .withInitializer(buyer -> {
                        buyer.setEmail(user.getEmail());
                        buyer.setFullName(getFullName(user));
                        user.setBuyer(buyer);
                    })
                    .withOpenMode(OpenMode.DIALOG)
                    .build()
                    .show();
        }
    }

    private String getFullName(ExtUser user) {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(user.getFirstName())) {
            sb.append(user.getFirstName());
        }
        if (Objects.nonNull(user.getMiddleName())) {
            sb.append(" ");
            sb.append(user.getMiddleName());
        }
        if (Objects.nonNull(user.getLastName())) {
            sb.append(" ");
            sb.append(user.getLastName());
        }
        return sb.toString();
    }
}