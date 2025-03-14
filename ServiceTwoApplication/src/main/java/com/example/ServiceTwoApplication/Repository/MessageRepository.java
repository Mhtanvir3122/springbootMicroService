package com.example.ServiceTwoApplication.Repository;


import com.example.ServiceTwoApplication.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}