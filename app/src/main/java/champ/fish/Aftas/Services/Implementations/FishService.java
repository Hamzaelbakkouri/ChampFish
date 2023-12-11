package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Exeptions.NotFoundExeption;
import champ.fish.Aftas.Models.DTO.Fish.FishDTO;
import champ.fish.Aftas.Models.DTO.Fish.FishDTOresp;
import champ.fish.Aftas.Models.Entities.Fish;
import champ.fish.Aftas.Models.Entities.Level;
import champ.fish.Aftas.Repositories.FishRepository;
import champ.fish.Aftas.Repositories.LevelRepository;
import champ.fish.Aftas.Services.Interfaces.Fish_Interface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FishService implements Fish_Interface {
    private FishRepository fishRepository;
    private LevelRepository levelRepository;

    private ModelMapper modelMapper;

    @Override
    public FishDTOresp create(FishDTO fishDTO) {
        Optional<Level> level = this.levelRepository.findById(fishDTO.getLevel_id());
        if (level.isPresent()) {
            Fish Fish = modelMapper.map(fishDTO, Fish.class);
            Fish.setLevel(level.get());
            return modelMapper.map(this.fishRepository.save(Fish), FishDTOresp.class);
        }
        throw new NotFoundExeption("level not found");
    }

    @Override
    public FishDTOresp update(FishDTO fishDTO) {
        try {
            Optional<Level> level = this.levelRepository.findById(fishDTO.getLevel_id());
            if (level.isPresent()) {
                Fish Fish = modelMapper.map(fishDTO, Fish.class);
                Fish.setLevel(level.get());
                Fish.setName(fishDTO.getName());
                return modelMapper.map(this.fishRepository.save(Fish), FishDTOresp.class);
            }
            throw new NotFoundExeption("level not found");
        } catch (Exception ex) {
            throw new NotFoundExeption("Level Not Found");
        }
    }

    @Override
    public Boolean delete(String id) {
        try {
            Optional<Fish> levelDTOresp = this.fishRepository.findById(id);
            if (levelDTOresp.isPresent()) {
                this.fishRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new NotFoundExeption("Fish Not Found");
        }
    }

    @Override
    public FishDTOresp get(String id) {
        try {
            Optional<Fish> levelDTOresp = this.fishRepository.findById(id);
            return modelMapper.map(levelDTOresp.get(), FishDTOresp.class);
        } catch (Exception ex) {
            throw new NotFoundExeption("Fish Not Found");
        }
    }

    @Override
    public List<FishDTOresp> getAll() {
        try {
            List<Fish> levelList = this.fishRepository.findAll();
            return levelList.stream().map(level -> modelMapper.map(level, FishDTOresp.class)).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new NotFoundExeption("Fishs Not Found");
        }
    }
}
