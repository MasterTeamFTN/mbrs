// WARNING: This file was autogenerated by MagicDraw MBRS plugin.
// Do not update it because if you run generator again you changes will be deleted

package ${class.typePackage};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ${class.typePackage?keep_before(".")}.model.${class.name};

import java.util.List;
import java.util.Date;

@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Long> {

}
