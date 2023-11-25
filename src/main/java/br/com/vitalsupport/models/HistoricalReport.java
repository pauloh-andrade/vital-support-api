package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.HistoricalReportRequestDto;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name="T_VSMVP_HISTORICO_RELATORIOS")
public class HistoricalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_relatos")
    private Long totalReports;

    @Column(name="total_confirmado")
    private Long totalConfirmed;

    @Column(name="id_cidade")
    private Long cityId;

    public HistoricalReport() {
    }

    public HistoricalReport(Long totalReports, Long totalConfirmed, Long cityId) {
        this.totalReports = totalReports;
        this.totalConfirmed = totalConfirmed;
        this.cityId = cityId;
    }
    public HistoricalReport(HistoricalReportRequestDto historicalReportRequestDto) {
        this.totalReports = historicalReportRequestDto.totalReports();
        this.totalConfirmed = historicalReportRequestDto.totalConfirmed();
        this.cityId = historicalReportRequestDto.cityId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalReports() {
        return totalReports;
    }

    public void setTotalReports(Long totalReports) {
        this.totalReports = totalReports;
    }

    public Long getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Long totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}

