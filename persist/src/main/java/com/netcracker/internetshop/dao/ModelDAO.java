package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Model;

import java.util.List;

public interface ModelDAO {

    List<Model> getModels();

    List<Model> getModelsByTypeId(String typeId);
}
