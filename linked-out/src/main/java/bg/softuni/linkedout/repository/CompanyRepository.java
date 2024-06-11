package bg.softuni.linkedout.repository;

import bg.softuni.linkedout.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsCompanyByName(String name);
}
