package com.array.entity;

import com.array.entity.base.BaseEntity;
import com.array.entity.enums.Role;
import com.array.entity.helpers.EntityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author XIII
 */
@Entity
@Table(name = EntityConstants.UserRole.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {

    @Column(length = 36, name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role;
}
