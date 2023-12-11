package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Exeptions.NotFoundExeption;
import champ.fish.Aftas.Models.DTO.Level.LevelDTO;
import champ.fish.Aftas.Models.DTO.Level.LevelDTOresp;
import champ.fish.Aftas.Models.Entities.Level;
import champ.fish.Aftas.Repositories.LevelRepository;
import champ.fish.Aftas.Services.Interfaces.Level_Interface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LevelService implements Level_Interface {
    private LevelRepository levelRepository;

    private ModelMapper modelMapper;

    @Override
    public LevelDTOresp create(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO, Level.class);
        return modelMapper.map(this.levelRepository.save(level), LevelDTOresp.class);
    }

    @Override
    public LevelDTOresp update(LevelDTO levelDTO) {
        Level level = modelMapper.map(this.levelRepository.findById(levelDTO.getCode()).get(), Level.class);

        Level level1 = modelMapper.map(levelDTO, Level.class);
        level1.setCode(levelDTO.getCode());
        return modelMapper.map(this.levelRepository.save(level1), LevelDTOresp.class);

    }

    @Override
    public Boolean delete(Integer id) {
        try {
            Optional<Level> levelDTOresp = this.levelRepository.findById(id);
            if (levelDTOresp.isPresent()) {
                this.levelRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new NotFoundExeption("Level Not Found");
        }
    }

    @Override
    public LevelDTOresp get(Integer id) {
        try {
            Optional<Level> levelDTOresp = this.levelRepository.findById(id);
            return modelMapper.map(levelDTOresp.get(), LevelDTOresp.class);
        } catch (Exception ex) {
            throw new NotFoundExeption("Level Not Found");
        }
    }

    @Override
    public List<LevelDTOresp> getAll() {
        try {
            List<Level> levelList = this.levelRepository.findAll();
            return levelList.stream().map(level -> modelMapper.map(level, LevelDTOresp.class)).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new NotFoundExeption("Levels Not Found");
        }
    }
}
