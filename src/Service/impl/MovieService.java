package Service.impl;
import Dao.impl.MovieDAO;
import Service.IMovieService;
import model.MovieModel;

import java.util.List;

public class MovieService implements IMovieService {

    @Override
    public int save(MovieModel movieModel) {
        return MovieDAO.getInstance().insert(movieModel);
    }

    @Override
    public List<MovieModel> selectAll() {
        return MovieDAO.getInstance().selectAll();
    }

    @Override
    public MovieModel selectById(MovieModel movieModel) {
        return MovieDAO.getInstance().selectById(movieModel);
    }

    @Override
    public int update(MovieModel movieModel) {
        return MovieDAO.getInstance().update(movieModel);
    }

    @Override
    public int delete(MovieModel movieModel) {
       return MovieDAO.getInstance().delete(movieModel);
    }

    public List<MovieModel> selectStatus() {
        return MovieDAO.getInstance().selectStatus();
    }
}
