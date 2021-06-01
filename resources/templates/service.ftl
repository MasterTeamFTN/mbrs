<#assign repositoryName="${class.name?uncap_first}Repository">
package ${class.typePackage};

import lombok.RequiredArgsConstructor;
import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.repository.${class.name}Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.Exception;

@Service
@RequiredArgsConstructor
public class ${class.name}GenService {
    private final ${class.name?cap_first}Repository ${repositoryName};

    public ${class.name} findById(Long id) {
        return ${repositoryName}.findById(id)
            .orElseThrow(() -> new Exception("${class.name?uncap_first}-not-found"));
    }

    public List<${class.name}> findAll() {
        return ${repositoryName}.findAll()
    }
}
