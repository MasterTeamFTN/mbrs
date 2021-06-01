package ${class.typePackage};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import uns.ftn.mbrs.model.*;

import uns.ftn.mbrs.model.${class.name};


@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Long> {

}
