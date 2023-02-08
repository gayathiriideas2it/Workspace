package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role {

	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int roleID;
	@Column(name="role_name")
	@NotBlank(message="Role Name is mandatory")
	String roleName;
	@Column(name="row_version")
	@LastModifiedDate
	LocalDateTime rowVersion;

}
