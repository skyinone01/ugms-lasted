package com.ugms.backend.service.entity.mysql;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by roy on 2017/3/7.
 */
@Entity
@Table(name = "t_user_product")
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6969901142245834369L;

	public enum Status { UNACCEPTED, ACCEPTED }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "user_id")
	private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "favourite", nullable = false)
	private boolean favourite;


	public Member() {}

	public Member(Long userId, Status status) {
		this.userId = userId;
		this.status = status;
	}

	public Member(Long userId, Long productId, Integer roleId, Status status) {
		this.userId = userId;
		this.productId = productId;
		this.roleId = roleId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	@PrePersist
	public void prePersist() {
		this.favourite = false;
	}
}
