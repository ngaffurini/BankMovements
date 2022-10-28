package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.mapper.DominiMapper;
import it.nicola.bankmovements.model.FiltriDomini;
import it.nicola.bankmovements.repository.DominiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DominiService {

    private DominiRepository dominiRepository;
    private DominiMapper dominiMapper;

    public DominiService(DominiRepository dominiRepository, DominiMapper dominiMapper) {
        this.dominiRepository = dominiRepository;
        this.dominiMapper = dominiMapper;
    }

    public DominiDto getDominioByCodiceAbi(String codiceAbi){
        return dominiMapper.toDto(dominiRepository.getFirstByCodiceAbi(codiceAbi));
    }

    public List<String> getDistinctCategoriaDomini(){
        return dominiRepository.getListCategorieDistinct();
    }

    public DominiDto getDominioByDescrizione(String descrizione) {
        return dominiMapper.toDto(dominiRepository.findFirstByDescrizione(descrizione));
    }

    public DominiDto getDominioByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi) {
        return dominiMapper.toDto(dominiRepository.findFirstByDescrizioneAndCodiceAbi(descrizione, codiceAbi));
    }

    public List<DominiDto> getDominiByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi) {
        return dominiMapper.toDtos(dominiRepository.findByDescrizioneAndCodiceAbi(descrizione, codiceAbi));
    }

    public DominiDto insertDominiEntity(DominiDto dominio){
        DominiEntity dom = dominiMapper.toEntity(dominio);
        return dominiMapper.toDto(dominiRepository.save(dom));
    }

    public List<String> getListCategorieByDescrizione(String descrizione){
        List<DominiEntity> domini = dominiRepository.findByDescrizione(descrizione);

        return domini.stream().map(DominiEntity::getCategoria).collect(Collectors.toList());
    }

    public Page<DominiDto> getFilteredList(FiltriDomini request, PageRequest pageRequest) {
        Page<DominiEntity> dominiList = dominiRepository.getFilteredList(request, pageRequest);

        return new PageImpl<>(
                dominiMapper.toDtos(dominiList.getContent()),
                dominiList.getPageable(),
                dominiList.getTotalElements());
    }

    public void update(DominiDto dominiDto) {
        dominiRepository.save(dominiMapper.toEntity(dominiDto));
    }

    public void delete(DominiDto dominiDto){
        dominiRepository.delete(dominiMapper.toEntity(dominiDto));
    }
}
