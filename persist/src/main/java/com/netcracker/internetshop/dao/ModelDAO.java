package com.netcracker.internetshop.dao;

import com.netcracker.internetshop.entity.catalog.Model;

import java.util.List;

/**
 * Created by PC on 27.04.2017.
 */
public interface ModelDAO {

    List<Model> getModels();

    List<Model> getModelsByTypeId(String typeId);
}
