package com.db.dao;

import java.util.List;

import com.db.model.BasicAddressesModel;

public interface BasicAddressesDao {
    public List<BasicAddressesModel> getSection(String _regionCode);
    public List<BasicAddressesModel> getStreet(String _sectionCode);
}
