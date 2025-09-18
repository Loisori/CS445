package com.example.demo.Sercive;

import com.example.demo.Entity.Favourites;
import com.example.demo.Repository.FavouritesRepository;
import com.example.demo.Sercive.impl.FavouritesServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouritesService implements FavouritesServiceimpl {
    @Autowired
    private FavouritesRepository favouritesrRepository;
    @Override
    public void SaveFavourites(Favourites favourites) {
favouritesrRepository.save(favourites);
    }

    @Override
    public void DeleteFavourites(Long id) {
        favouritesrRepository.deleteById(id);

    }

    @Override
    public Favourites GetFavourites(Long id) {
        return favouritesrRepository.findById(id).orElse(null);
    }
}
