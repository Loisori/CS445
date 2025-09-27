package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistory {

    public enum TransactionType {
        NAP_TIEN,
        RUT_TIEN
    }

    public enum TransactionStatus {
        SUCCESS,
        PENDING,
        FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Số tiền giao dịch
    @Column(nullable = false)
    private Double amount;

    // Loại giao dịch: NAP_TIEN hoặc RUT_TIEN
    @Enumerated(EnumType.STRING)  // <--- lưu dạng text: "NAP_TIEN"
    @Column(nullable = false)
    private TransactionType type;

    // Trạng thái: SUCCESS / PENDING / FAILED
    //THÀNH CÔNG / ĐANG CHỜ XỬ LÝ / THẤT BẠI
    @Enumerated(EnumType.STRING)  // <--- lưu dạng text: "SUCCESS"
    @Column(nullable = false)
    private TransactionStatus status;

    // Thời gian thực hiện
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Liên kết với user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
