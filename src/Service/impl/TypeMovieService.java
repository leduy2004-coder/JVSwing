package Service.impl;
import Dao.impl.TypeMovieDAO;
import Service.ITypeMovieService;
import model.TypeMovieModel;

import java.util.List;

public class TypeMovieService implements ITypeMovieService {


    @Override
    public TypeMovieModel selectById(TypeMovieModel typeMovieModel) {
        return TypeMovieDAO.getInstance().selectById(typeMovieModel);
    }

    @Override
    public TypeMovieModel selectByName(TypeMovieModel typeMovieModel) {
        return TypeMovieDAO.getInstance().selectByName(typeMovieModel);
    }

    @Override
    public List<TypeMovieModel> selectAll() {
        return TypeMovieDAO.getInstance().selectAll();
    }
}
