package com.example.quiz.service;

import com.example.quiz.dto.ResponseDTO;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IResponse;
import com.example.quiz.model.Response;
import com.example.quiz.repository.ResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService implements IResponse {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ResponseService(ResponseRepository responseRepository){
        this.responseRepository=responseRepository;
    }
    
    @Override
    public List<ResponseDTO> findAll() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream()
                .map(response -> modelMapper.map(response, ResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ResponseDTO findById(int id) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(response, ResponseDTO.class);
    }

    @Override
    public ResponseDTO save(ResponseDTO responseDTO) {
        Response response= modelMapper.map(responseDTO, Response.class);
        responseRepository.save(response);
        return modelMapper.map(response, ResponseDTO.class);
    }


    public ResponseDTO deleteById(int id){
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        responseRepository.deleteById(id);
        return modelMapper.map(response, ResponseDTO.class);
    }

    @Override
    public ResponseDTO update(ResponseDTO responseDTO) {
        Response response = responseRepository.findById(responseDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + responseDTO.getId()));
        response.setTextResponse(responseDTO.getTextResponse());
        responseRepository.save(response);
        return modelMapper.map(response, ResponseDTO.class);
    }

}
