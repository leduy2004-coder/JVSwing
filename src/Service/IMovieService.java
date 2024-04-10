package Service;

import model.EmployeeModel;
import model.MovieModel;

import java.util.List;

public interface IMovieService {
    public int save(MovieModel movieModel);
    public List<MovieModel> selectAll();
    public MovieModel selectById(MovieModel movieModel);
    public int update(MovieModel movieModel);
    public int delete(MovieModel movieModel);
}
