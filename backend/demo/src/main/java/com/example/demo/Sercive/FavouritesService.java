//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Favourites;
import com.example.demo.Repository.FavouritesRepository;
import com.example.demo.Sercive.impl.FavouritesServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritesService implements FavouritesServiceimpl {
    private final FavouritesRepository favouritesrRepository;

    @Override
    public List<Favourites> GetUser_id(long id) {
        return favouritesrRepository.findByUser_UserId((id));
    }


    public void DeleteProjects(Long id) {
        this.favouritesrRepository.deleteByProjectId(id);
    }

    public void SaveFavourites(Favourites favourites) {
        this.favouritesrRepository.save(favourites);
    }

    public void DeleteFavourites(Long id) {
        this.favouritesrRepository.deleteById(id);
    }

    public Favourites GetFavourites(Long id) {
        return (Favourites) this.favouritesrRepository.findById(id).orElse(null);
    }
}
