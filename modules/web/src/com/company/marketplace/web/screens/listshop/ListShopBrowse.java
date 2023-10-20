package com.company.marketplace.web.screens.listshop;

import com.company.marketplace.entity.*;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@UiController("marketplace_ListShopWithoutProducts.browse")
@UiDescriptor("list-for-manufacturer.xml")
@LookupComponent("soldProductsTable")
@LoadDataBeforeShow
public class ListShopBrowse extends StandardLookup<ListShop> {
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionLoader<SoldProduct> soldProductsDl;
    @Inject
    private PickerField<Manufacturer> manufacturerField;
    @Inject
    private PickerField<Product> productField;
    @Inject
    private TextField<Integer> quantityField;
    @Inject
    private PickerField<Shop> shopField;
    @Inject
    private CollectionLoader<Shop> shopsDl;

    @Subscribe
    public void onInit(InitEvent event) {
        shopsDl.setParameter("null", null);
        shopsDl.load();
    }

    @Subscribe("calculateWithNumberBtn")
    public void onCalculateWithNumberBtnClick(Button.ClickEvent event) {
            soldProductsDl.load();
    }

    @Subscribe("calculateWithoutProductBtn")
    public void onCalculateWithoutProductBtnClick(Button.ClickEvent event) {
        if (Objects.nonNull(productField.getValue())) {
            shopsDl.setQuery("select e from marketplace_Shop e where e.products <> ALL "
                    + "(select s from marketplace_SoldProduct s where s.product = :product)");
            shopsDl.setParameter("product", productField.getValue());
            shopsDl.load();
        }
    }

    @Install(to = "soldProductsDl", target = Target.DATA_LOADER)
    private List<SoldProduct> soldProductsDlLoadDelegate(LoadContext<SoldProduct> loadContext) {
        Long quantity = Objects.isNull(quantityField.getValue()) ? -1L : quantityField.getValue();
        loadContext.getQuery().setParameter("quantity", quantity);
        loadContext.getQuery().setParameter("shop", shopField.getValue());
        loadContext.getQuery().setParameter("manufacturer", manufacturerField.getValue());
        return dataManager.loadList(loadContext);
    }
}