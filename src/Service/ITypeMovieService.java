package Service;

import model.MovieModel;
import model.TypeMovieModel;

import java.util.List;

public interface ITypeMovieService {
    public TypeMovieModel selectById(TypeMovieModel typeMovieModel);
    public TypeMovieModel selectByName(TypeMovieModel typeMovieModel);
    public List<TypeMovieModel> selectAll();
}
