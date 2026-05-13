package com.aion.logos.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 灵识节点 (Logos Node) - Agent 实体
 * 代表模拟世界中的一个独立个体，具有独特的立场与性格
 */
@Data
@Builder
public class LogosAgent {
    /**
     * Agent 唯一标识
     */
    private String id;

    /**
     * Agent 名称
     */
    private String name;

    /**
     * 身份立场 (如：保守派、破坏者、哲学家)
     */
    private String stance;

    /**
     * 专业背景
     */
    private String background;

    /**
     * 性格画像 (如：严谨、激进、悲悯)
     */
    private String persona;

    /**
     * 核心价值观
     */
    private String coreValues;
}
