package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Favourites;

public interface FavouritesServiceimpl {
    public void SaveFavourites(Favourites favourites);
    public void DeleteFavourites(Long id);
     public Favourites GetFavourites(Long id);

 }
