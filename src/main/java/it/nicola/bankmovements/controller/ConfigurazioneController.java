package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.dto.ConfigurazioneDto;
import it.nicola.bankmovements.model.JsonPageRequest;
import it.nicola.bankmovements.service.impl.ConfigurazioneService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configurazione")
public class ConfigurazioneController {

    private final ConfigurazioneService configurazioneService;

    public ConfigurazioneController(ConfigurazioneService configurazioneService) {
        this.configurazioneService = configurazioneService;
    }

    @GetMapping("/list/all")
    public Page<ConfigurazioneDto> getListConfigurazioneAll(JsonPageRequest pageRequest){
        return configurazioneService.getListConfigurazioneAll(pageRequest.toPageRequest());
    }


    @GetMapping("/{id}")
    public ConfigurazioneDto getConfigurazioneById(@PathVariable(name = "id") String id){
        return configurazioneService.getConfigurazioneById(id);
    }

    @GetMapping("/{key}")
    public ConfigurazioneDto getConfigurazioneByKey(@PathVariable("key") String key){
        return configurazioneService.getConfigurazioneByKey(key);
    }

    @PutMapping("/update")
    public void update(ConfigurazioneDto configurazioneDto){
        configurazioneService.update(configurazioneDto);
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        configurazioneService.delete(id);
    }
}
