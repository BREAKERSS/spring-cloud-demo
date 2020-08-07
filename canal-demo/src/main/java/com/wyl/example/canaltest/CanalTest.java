package com.wyl.example.canaltest;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalTest {
    public static void main(String[] args) throws Exception {

        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("192.168.0.6", 11111),
                "example", "root", "root");
        connector.connect();
        connector.subscribe(".*\\..*");
        connector.rollback();

        while (true) {
            Message message = connector.getWithoutAck(100); // 获取指定数量的数据
            long batchId = message.getId();
            if (batchId == -1 || message.getEntries().isEmpty()) {
                Thread.sleep(1000);
                continue;
            }
//             System.out.println(message.getEntries());
            printEntries(message.getEntries());
            connector.ack(batchId);// 提交确认，消费成功，通知server删除数据
//            connector.rollback(batchId);// 处理失败, 回滚数据，后续重新获取数据
        }
    }

    private static void printEntries(List<Entry> entries) throws Exception {
        for (Entry entry : entries) {
            if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) {
                continue;
            }

            CanalEntry.RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());

            EventType eventType = rowChange.getEventType();
//            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));
//te_answer
//            System.out.println(entry.getHeader().getTableName());
            if (!entry.getHeader().getTableName().equals("te_answer")) {
                continue;
            }
            for (RowData rowData : rowChange.getRowDatasList()) {
                switch (rowChange.getEventType()) {
                    case INSERT:
                        printColumns(entry, rowData.getAfterColumnsList());
                        break;
//                    case UPDATE:
//                        System.out.println("UPDATE ");
//                        printColumns(rowData.getAfterColumnsList());
//                        break;
//                    case DELETE:
//                        System.out.println("DELETE ");
//                        printColumns(rowData.getBeforeColumnsList());
//                        break;

                    default:
                        break;
                }
            }
        }
    }

    private static void printColumns(Entry entry, List<Column> columns) {
        System.out.print("insert into " + entry.getHeader().getTableName() + " ( ");
        for (int i = 0; i < columns.size(); i++) {
            System.out.print(columns.get(i).getName());
            if (i != columns.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("  )VALUES (");
        for (int i = 0; i < columns.size(); i++) {
            System.out.print("'" + columns.get(i).getValue() + "'");
            if (i != columns.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println(");");
        // "insert db_affairs_menu values (#{id},#{name},#{parentId},#{icon},#{urlAddress},#{urlRoute},#{orderNum},#{photo},#{hidden},#{alwaysShow},\n" +
        //         "        #{isDisabled},#{deleted},#{operator},#{createTime},#{modifyUser},#{modifyTime})";
    }

    private static void printColumns(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }
}
