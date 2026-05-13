package com.aion.logos.service;

import com.aion.logos.entity.LogosAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 身份印刻引擎 (Identity Imprinting Engine)
 * 负责批量生成并初始化具有完全不同背景的 Agent
 */
@Slf4j
@Service
public class IdentityEngineService {

    /**
     * 批量印刻 Agent
     * 使用 Java 21 虚拟线程处理海量 Agent 的初始化逻辑
     *
     * @param count 初始化的 Agent 数量
     * @return Agent 列表
     */
    public List<LogosAgent> imprintAgents(int count) {
        log.info("开始启动大规模身份印刻任务，目标数量：{}", count);
        List<LogosAgent> agents = new ArrayList<>();

        // 使用虚拟线程池，应对海量 Agent 的初始化计算
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < count; i++) {
                int finalI = i;
                executor.submit(() -> {
                    LogosAgent agent = LogosAgent.builder()
                            .id("AGENT-" + finalI)
                            .name("节点-" + finalI)
                            .stance(generateStance(finalI))
                            .persona("性格分析中...")
                            .background("跨行业领域专家")
                            .build();
                    synchronized (agents) {
                        agents.add(agent);
                    }
                });
            }
        }

        log.info("身份印刻完成，成功生成 {} 个灵识节点", agents.size());
        return agents;
    }

    private String generateStance(int index) {
        String[] stances = {"极致保守", "极具破坏力", "悲天悯人", "绝对客观", "混沌无序"};
        return stances[index % stances.length];
    }
}
