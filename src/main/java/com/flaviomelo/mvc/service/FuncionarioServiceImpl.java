package com.flaviomelo.mvc.service;

import com.flaviomelo.mvc.dao.FuncionarioDao;
import com.flaviomelo.mvc.domain.Funcionario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional( readOnly = false )
public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioDao dao;
    @Override
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Override
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarTodos() {
        return dao.findAll();
    }
}
