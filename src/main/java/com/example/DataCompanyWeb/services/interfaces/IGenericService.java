package com.example.DataCompanyWeb.services.interfaces;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface IGenericService<T, E> {
    ModelAndView GetAll();
    ModelAndView GetById(Long id);
    ModelAndView Post(@Valid T entity, BindingResult bindingResult);
    ModelAndView Delete(Long id);
    ModelAndView Update(Long id, @Valid E entity, BindingResult bindingResult);
}
