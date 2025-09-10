package br.edu.ifgoias.academico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.dto.AlunoMapper;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRep;

    @Autowired
    private AlunoMapper alunoMapper;

    @Transactional
    public Page<AlunoDTO> listaAluno(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Aluno> pageAlunos = (search == null || search.isEmpty())
                ? alunoRep.findAll(pageable)
                : alunoRep.findByNomeContainingIgnoreCase(search, pageable);
        return pageAlunos.map(alunoMapper::toDTO);
    }

    @Transactional
    public AlunoDTO alunoById(Integer id) {
        return alunoRep.findById(id)
                .map(alunoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
    }

    @Transactional
    public AlunoDTO alunoInserir(AlunoDTO aDTO) {
        if (aDTO == null || aDTO.getNome() == null) {
            throw new IllegalArgumentException("Dados do aluno inválidos");
        }
        Aluno a = alunoMapper.toEntity(aDTO);
        alunoRep.save(a);
        return alunoMapper.toDTO(a);
    }

    @Transactional
    public AlunoDTO alunoAlterar(Integer id, AlunoDTO aDTO) {
        Aluno a = alunoRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        a.setNome(aDTO.getNome());
        a.setSexo(aDTO.getSexo());
        a.setDt_nasc(aDTO.getDt_nasc());
        alunoRep.save(a);
        return alunoMapper.toDTO(a);
    }

    public void alunoDeletar(Integer id) {
        Aluno a = alunoRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        alunoRep.delete(a);
    }
}
