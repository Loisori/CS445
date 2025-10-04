//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Favourites;

import java.util.List;

public interface FavouritesServiceimpl {
    void SaveFavourites(Favourites favourites);

    void DeleteFavourites(Long id);

    Favourites GetFavourites(Long id);

    void DeleteProjects(Long id);
    List<Favourites> GetUser_id(long id);
}
