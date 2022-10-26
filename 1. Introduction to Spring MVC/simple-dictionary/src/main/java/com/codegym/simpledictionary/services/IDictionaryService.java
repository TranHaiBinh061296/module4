package com.codegym.simpledictionary.services;

import com.codegym.simpledictionary.model.Dictionary;

import java.util.List;

public interface IDictionaryService {
    List<Dictionary> findAll();
}
